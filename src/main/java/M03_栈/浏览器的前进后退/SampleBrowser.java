package M03_栈.浏览器的前进后退;

import lombok.Data;

/**
 * 使用前后栈实现浏览器的前进后退
 *
 * @author huangshuai
 * @date 2022/11/24
 */
public class SampleBrowser {

    public static void main(String[] args) {
        SampleBrowser browser = new SampleBrowser();
        browser.open("1");
        browser.open("2");
        browser.open("3");

        browser.goBack();
        browser.goBack();
        browser.goForward();
        browser.open("4");

        browser.goForward();
        browser.goBack();
        browser.goForward();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.checkCurrentPage();

    }

    private String currentPage;

    private LinkedListBasedStack backStack = new LinkedListBasedStack();

    private LinkedListBasedStack forwardStack = new LinkedListBasedStack();

    public void open(String url) {
        if (currentPage != null) {
            //  将当前页放入回退页面
            this.backStack.push(currentPage);
            // 打开了新页面，将前进页清除
            this.forwardStack.clear();
        }
        showUrl(url, "open");
    }

    public void goBack() {
        if (canGoBack()) {
            // 后退，将当前页面放入前进列表中
            String backUrl = this.backStack.pop();
            this.forwardStack.push(currentPage);
            showUrl(backUrl, "back");
        }
    }

    public void goForward() {
        if (canGoForward()) {
            // 前进，将当前页面放入后退列表中
            String forwardUrl = this.forwardStack.pop();
            this.backStack.push(currentPage);
            showUrl(forwardUrl, "forward");
        }
    }

    public boolean canGoBack() {
        return this.backStack.getSize() > 0;
    }

    public boolean canGoForward() {
        return this.forwardStack.getSize() > 0;
    }

    public void checkCurrentPage() {
        System.out.println("Current page is: " + this.currentPage);
    }

    public void showUrl(String url, String operation) {
        this.currentPage = url;
        System.out.println(operation + " currentPage == " + url);
    }

    /**
     * A LinkedList based Stack implementation.
     */
    @Data
    public static class LinkedListBasedStack {
        private int size;
        private Node top;

        public void push(String data) {
            Node node = new Node(data, top);
            top = node;
            size++;
        }

        public String pop() {
            if (top != null) {
                String data = top.data();
                top = top.next();
                size--;
                return data;
            }
            return null;
        }

        public void clear() {
            top = null;
            size = 0;
        }

        /**
         * 节点
         */
        public record Node(String data, Node next) { }
    }

}
