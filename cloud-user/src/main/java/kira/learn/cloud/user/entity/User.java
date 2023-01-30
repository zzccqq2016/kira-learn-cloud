package kira.learn.cloud.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/30 16:04
 */
@Data
@Entity
@Table(name = "t_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    @Basic
    private String username;
    @Basic
    private String password;
    @Basic
    private Integer status;
    @Basic
    private String realName;
    @Basic
    private String email;
    @Basic
    private String mobile;
    @Basic
    private String position;
    @Basic
    private String company;
    @Basic
    private String industry;
    @Basic
    @Column(name = "create_at",insertable = false,updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;
    @Basic
    @Column(name = "update_at",insertable = false,updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateAt;


}
