package model;

public class Insurance {

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
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
