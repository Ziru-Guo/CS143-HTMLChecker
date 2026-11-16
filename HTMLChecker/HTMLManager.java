// Names: Ziru Guo, Yunfeng Duan
// Class: CS 143
// Assignment: Program #4: HTML Checker

import java.util.*;

public class HTMLManager {
    private Queue<HTMLTag> tags;

    public HTMLManager(Queue<HTMLTag> html) {
        if (html == null) {
            throw new IllegalArgumentException();
        }
        this.tags = new LinkedList<HTMLTag>();
        int size = html.size();
        for (int i = 0; i < size; i++) {
            HTMLTag tag = html.remove();
            this.tags.add(tag);
            html.add(tag);
        }
    }

    public Queue<HTMLTag> getTags() {
        return this.tags;
    }

    public String toString() {
        String result = "";
        int size = this.tags.size();
        for (int i = 0; i < size; i++) {
            HTMLTag current = this.tags.remove();
            result += current.toString().trim();
            this.tags.add(current);
        }
        return result;
    }

    public void fixHTML() {   
    }
}