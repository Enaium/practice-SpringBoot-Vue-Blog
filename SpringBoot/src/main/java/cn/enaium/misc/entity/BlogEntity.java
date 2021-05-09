package cn.enaium.misc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Enaium
 */
@Data
@TableName("blog")
public class BlogEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String title;
    private Long memberId;
    private Date postDate;
    private String content;
}
