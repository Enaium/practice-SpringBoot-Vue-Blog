package cn.enaium.misc.common.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Enaium
 */
@Getter
@Setter
public class MemberDTO {
    private String username;
    private String password;
}
