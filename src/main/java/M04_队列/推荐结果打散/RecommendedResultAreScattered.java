package M04_队列.推荐结果打散;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 推荐结果打散
 *
 * @author Chimm Huang
 * @date 2024/1/10
 */
public class RecommendedResultAreScattered {
    /*
        题目标题：推荐结果打散
        题目内容：
            假设你正在使用快手，我给你一组推荐数据，你需要按照我给定的规则，返回我想要的推荐内容（视频用v表示，图片用p表示）
            现在需要对推荐数据返回的结果进行打散，使得图片每 n 个出现 1 次，并保证该图片最早出现的位置不变（图片出现的相对顺序不变）
            比如我们的推荐池为： [v0,v1,v2,p3,p4,p5,v6,p7,v8,v9]
            要求图片每 3 个视频，出现 1 次。则该推荐池最终结果为：[v0,v1,v2,p3,v6,v8,v9,p4,v9] 不满足要求的直接删掉
     */

    public static void main(String[] args) {
        Resource[] resourceArr = new Resource[10];
        resourceArr[0] = new Resource(1, "视频0");
        resourceArr[1] = new Resource(1, "视频1");
        resourceArr[2] = new Resource(1, "视频2");
        resourceArr[3] = new Resource(2, "图片3");
        resourceArr[4] = new Resource(2, "图片4");
        resourceArr[5] = new Resource(2, "图片5");
        resourceArr[6] = new Resource(1, "视频6");
        resourceArr[7] = new Resource(2, "图片7");
        resourceArr[8] = new Resource(1, "视频8");
        resourceArr[9] = new Resource(1, "视频9");

        System.out.print("初始化的推荐池：");
        print(resourceArr);

        Resource[] recommendArr = scatter(resourceArr, 3);

        System.out.print("打散后的推荐池：");
        print(recommendArr);
    }


    static class Resource {
        // 1-视频、2-图片
        int type;
        String name;

        public Resource(int type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    static Queue<Resource> pictureQueue = new ArrayDeque<>();
    static Queue<Resource> vedioQueue = new ArrayDeque<>();
    public static Resource[] scatter(Resource[] resourceArr, int splitIndex) {
        List<Resource> list = new ArrayList<>();
        int videoTimes = 0;
        for (int i = 0; i < resourceArr.length; i++) {
            Resource resource = resourceArr[i];
            // 视频
            if (resource.type == 1) {
                vedioQueue.add(resource);
            }
            // 图片
            else {
                pictureQueue.add(resource);
            }
        }

        for (Resource resource : vedioQueue) {
            if (videoTimes == 3 && !pictureQueue.isEmpty()) {
                list.add(pictureQueue.remove());
                videoTimes = 0;
            }

            list.add(resource);
            videoTimes++;
        }
        if (videoTimes == 3 && !pictureQueue.isEmpty()) {
            list.add(pictureQueue.remove());
        }
        return list.toArray(new Resource[0]);
    }

    public static void print(Resource[] resources) {
        for (int i = 0; i < resources.length; i++) {
            System.out.print(resources[i].name + " ");
        }
        System.out.println();
    }
}
