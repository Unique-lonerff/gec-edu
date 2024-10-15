import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import personal.zx.mybatispuls.MybatisPulsApplication;
import personal.zx.mybatispuls.entity.dos.Users;
import personal.zx.mybatispuls.entity.mapper.UsersMapper;

import java.util.Arrays;
import java.util.List;

/**
 * @author 小z
 * @date 2024年10月14日 下午3:36
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPulsApplication.class)
public class TestUsers {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void test1() {
        List<Users> list = usersMapper.selectList(null);
        for (Users users : list) {
            System.out.println(users);
        }
    }

    @Test
    public void test2() {
        long id = 5;
        Users users = usersMapper.selectById(id);
        System.out.println(users);
    }

    @Test
    public void test3() {
        Users users = new Users();
        users.setName("77");
        users.setAge(677);
        users.setEmail("2677726@qq.com");
        int result = usersMapper.insert(users);
    }

    @Test
    public void test4() {
        long id = 6;
        Users users = usersMapper.selectById(id);
        users.setAge(30);
        usersMapper.updateById(users);
    }

    //分页测试
    @Test
    public void test5() {
        long pageNum = 2;
        long pageSize = 2;
        Page<Users> page = new Page<>(pageNum, pageSize);
        usersMapper.selectPage(page, null);
        List<Users> records = page.getRecords();
        for (Users users : records) {
            System.out.println(users);
        }

        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());

    }

    // (1) 根据ID删除
    @Test
    public void deleteByIdTest() {
        long id = 6L;
        int result = usersMapper.deleteById(id);
        System.out.println("根据ID删除结果：" + result);
    }

    // (2) 根据非主键删除（根据姓名删除）
    @Test
    public void deleteByNameTest() {


        // 物理删除方法：绕过逻辑删除

        /**
         * QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
         * queryWrapper.eq("id", 1L); // 根据ID查询
         * queryWrapper.eq("deleted", 0); // 强制指定未被逻辑删除的记录
         * int result = usersMapper.delete(queryWrapper); // 实际执行物理删除
         * System.out.println("物理删除结果：" + result);
         *
         *         or
         *
         * @Component
         * public class UsersService {
         *     @Autowired
         *     private SqlSession sqlSession;
         *     public void physicalDelete(Long id) {
         *         sqlSession.delete("delete from users where id = #{id}", id); // 真正的物理删除
         *     }
         * }
         */


        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Jone");
        int result = usersMapper.delete(queryWrapper);
        System.out.println("根据姓名删除结果：" + result);
    }

    // (3) 批量删除（根据ID批量删除）
    @Test
    public void batchDeleteByIdsTest() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        int result = usersMapper.deleteBatchIds(ids);
        System.out.println("批量删除结果：" + result);
    }

    // (4) 逻辑删除（根据ID进行逻辑删除）
    @Test
    public void logicalDeleteByIdTest() {
        long id = 4L;
        int result = usersMapper.deleteById(id); // 这里会触发逻辑删除
        System.out.println("逻辑删除结果：" + result);
    }


    //其他查询
    @Test
    public void test6() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 20,25);

        queryWrapper.like("name", "J");
        List<Users> usersList = usersMapper.selectList(queryWrapper);
        for (Users users : usersList) {
            System.out.println(users);
        }
    }


}
