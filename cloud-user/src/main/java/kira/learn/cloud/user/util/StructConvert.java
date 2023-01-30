package kira.learn.cloud.user.util;

import kira.learn.cloud.user.bean.UserDto;
import kira.learn.cloud.user.entity.User;
import org.mapstruct.Mapper;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/30 17:22
 */
@Mapper(componentModel = "spring")
public interface StructConvert {


    /**
     * UserDto 转换 User
     *
     * @param dto UserDto
     * @return User
     */
    User toUser(UserDto dto);


}