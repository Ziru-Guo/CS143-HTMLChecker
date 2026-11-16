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
        Stack<HTMLTag> openTags = new Stack<HTMLTag>();
        int size = this.tags.size();
        
        for (int i = 0; i < size; i++) {
            HTMLTag current = this.tags.remove();
            
            if (current.isSelfClosing()) {
                // Self-closing tags are added directly back to queue
                this.tags.add(current);
            } else if (current.isOpening()) {
                // Opening tags are added to queue and pushed onto stack
                this.tags.add(current);
                openTags.push(current);
            } else if (current.isClosing()) {
                // Check if closing tag matches the top opening tag on stack
                if (!openTags.isEmpty()) {
                    if (openTags.peek().matches(current)) {
                        this.tags.add(current);
                        openTags.pop();
                    } else {
                        // Fix mismatch by adding matching tag for top of stack
                        this.tags.add(openTags.pop().getMatching());
                    }
                }
            }
        }
        
        // Close any leftover unclosed opening tags in stack
        while (!openTags.isEmpty()) {
            this.tags.add(openTags.pop().getMatching());
        }
    }
}