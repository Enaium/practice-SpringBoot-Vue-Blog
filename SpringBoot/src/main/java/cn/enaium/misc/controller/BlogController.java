package cn.enaium.misc.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.enaium.misc.common.Result;
import cn.enaium.misc.common.dto.BlogDTO;
import cn.enaium.misc.common.dto.PostDTO;
import cn.enaium.misc.entity.BlogEntity;
import cn.enaium.misc.mapper.BlogMapper;
import cn.enaium.misc.mapper.MemberMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Enaium
 */
@RestController
public class BlogController {

    private final BlogMapper blogMapper;
    private final MemberMapper memberMapper;

    public BlogController(BlogMapper blogMapper, MemberMapper memberMapper) {
        this.blogMapper = blogMapper;
        this.memberMapper = memberMapper;
    }

    @GetMapping("/blogs")
    public Result<List<BlogDTO>> blogs() {
        var blogDTOStream = blogMapper.selectList(null).stream().map(it -> {
            var blogDTO = new BlogDTO();
            blogDTO.setId(it.getId());
            blogDTO.setTitle(it.getTitle());
            blogDTO.setMemberName(memberMapper.selectById(it.getMemberId()).getName());
            blogDTO.setPostDate(it.getPostDate());
            return blogDTO;
        }).collect(Collectors.toList());
        return new Result<>(200, "获取成功", blogDTOStream);
    }

    @PostMapping("/post")
    public Result<String> post(@RequestBody PostDTO postDTO) {
        var blogEntity = new BlogEntity();
        blogEntity.setId(null);
        blogEntity.setTitle(postDTO.getTitle());
        blogEntity.setMemberId(StpUtil.getLoginIdAsLong());
        blogEntity.setPostDate(new Date());
        blogEntity.setContent(postDTO.getContent());
        blogMapper.insert(blogEntity);
        return new Result<>(200, "发布成功", null);
    }

    @PostMapping("/edit/{id}")
    public Result<String> edit(@PathVariable(name = "id") String id, @RequestBody PostDTO postDTO) {

        var blogEntity = blogMapper.selectById(id);
        if (blogEntity == null) {
            return new Result<>(400, "没找到", null);
        } else if (blogEntity.getMemberId() != StpUtil.getLoginIdAsLong()) {
            return new Result<>(400, "只能修改自己的", null);
        }

        blogEntity.setTitle(postDTO.getTitle());
        blogEntity.setContent(postDTO.getContent());
        blogMapper.updateById(blogEntity);
        return new Result<>(200, "修改成功", null);
    }

    @GetMapping("/blog/{id}")
    public Result<BlogEntity> blog(@PathVariable(name = "id") Long id) {
        var blogEntity = blogMapper.selectById(id);

        if (blogEntity == null) {
            return new Result<>(400, "获取失败", null);
        }

        return new Result<>(200, "获取成功", blogEntity);
    }

    @PostMapping("/delete/{id}")
    public Result<String> delete(@PathVariable(name = "id") String id) {
        blogMapper.deleteById(id);
        return new Result<>(200, "删除成功", null);
    }
}
