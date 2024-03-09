package org.app.context;

/**
 * 作者:疏狂难除
 * 时间:2024-03-09
 */
public class BaseContext {

        public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

        public static void setCurrentId(Long id) {
            threadLocal.set(id);
        }

        public static Long getCurrentId() {
            return threadLocal.get();
        }

        public static void removeCurrentId() {
            threadLocal.remove();
        }
}
