package model;

import java.io.Serializable;

/**
 * Defines what an Insurance is. A Patient has insurance
 * @see Patient
 */
public class Insurance implements Serializable {

    private static final long serialVersionUID = -8264932588394084499L;

    private String name;
    private String groupId;
    private String memberId;

    /**
     * Constructor of the Insurance
     * @param name Insurance's name
     * @param groupId Insurance's group ID
     * @param memberId Patient member ID
     */
    public Insurance(String name, String groupId, String memberId) {
        this.setName(name);
        this.setGroupId(groupId);
        this.setMemberId(memberId);
    }

    /**
     * @return the name of the insurance
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Insurance name
     * @param name name of the company
     */
    public void setName(String name) {
        if (name == null || (name.trim()).isEmpty()) {
            throw new IllegalArgumentException("Name cannot be nothing");
        }
        this.name = name;
    }

    /**
     * @return the group Id
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Set's the group ID
     * @param groupId new number for group ID
     */
    public void setGroupId(String groupId) {
        if (groupId == null || (groupId.trim()).isEmpty()) {
            throw new IllegalArgumentException("This is not a valid Group ID");
        }
        this.groupId = groupId;
    }

    /**
     * Sets the memberID of the patient
     * @param memberId
     */
    public void setMemberId(String memberId) {
        if (memberId == null || (memberId.trim()).isEmpty()) {
            throw new IllegalArgumentException("This is not a valid Member ID");
        }
        this.memberId = memberId;
    }
}
