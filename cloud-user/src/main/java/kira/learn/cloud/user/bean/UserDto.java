package kira.learn.cloud.user.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/30 17:16
 */
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotEmpty
    @ApiModelProperty("用户名")
    private String username;
    @NotEmpty
    @ApiModelProperty("密码")
    private String password;
    @NotEmpty
    @ApiModelProperty("昵称")
    private String realName;
    @NotEmpty
    @ApiModelProperty("手机号")
    private String mobile;

}