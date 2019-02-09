package model;

import java.io.Serializable;

/**
 * Defines what an Insurance is
 */
public class Insurance implements Serializable {

    private static final long serialVersionUID = -8264932588394084499L;

    private String name;
    private String groupId;
    private String memberId;

    public Insurance(String name, String groupId, String memberId) {
        this.setName(name);
        this.setGroupId(groupId);
        this.setMemberId(memberId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || (name.trim()).isEmpty()) {
            throw new IllegalArgumentException("Name cannot be nothing");
        }
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        if (groupId == null || (groupId.trim()).isEmpty()) {
            throw new IllegalArgumentException("This is not a valid Group ID");
        }
        this.groupId = groupId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        if (memberId == null || (memberId.trim()).isEmpty()) {
            throw new IllegalArgumentException("This is not a valid Member ID");
        }
        this.memberId = memberId;
    }
}
