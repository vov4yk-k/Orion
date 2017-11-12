package com.orion.dao;

import com.orion.model.GroupMember;
import com.orion.model.User;

import java.util.List;

/**
 * Created by Vova on 13.05.2017.
 */
public interface GroupMemberDAO {
    public List<GroupMember> listGroupMember();
    public void addGroupMember(GroupMember groupMember);
    public void updateGroupMember(GroupMember groupMember);
    public void deleteGroupMember(GroupMember groupMember);
    public GroupMember getGroupMemberByID(Integer id);
}
