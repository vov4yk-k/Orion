package com.orion.dao;

import com.orion.model.GroupMember;

import java.util.List;

/**
 * Created by Vova on 13.05.2017.
 */
public interface GroupMemberDAO {
    public List<GroupMember> listGroupMember();
    public void addGroupMember(GroupMember groupMember);
    public void deleteGroupMember(Integer id);
    public GroupMember getGroupMemberByID(Integer id);
}
