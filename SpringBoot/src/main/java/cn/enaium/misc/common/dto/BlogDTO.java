package cn.enaium.misc.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Enaium
 */
@Data
public class BlogDTO {
    private Long id;
    private String title;
    private String memberName;
    private Date postDate;
}
