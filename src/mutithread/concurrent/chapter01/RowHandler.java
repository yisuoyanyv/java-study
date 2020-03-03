package mutithread.concurrent.chapter01;

import java.sql.ResultSet;

/**
 * RowHandle 接口只负责从数据库中查询出的结果集进行操作，至于最终返回成什么样的数据结构，需要自己去实现
 * @param <T>
 */
public interface RowHandler<T> {
    T handle(ResultSet rs);
}
