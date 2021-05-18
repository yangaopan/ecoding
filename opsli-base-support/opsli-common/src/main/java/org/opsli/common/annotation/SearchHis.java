/**
 * Copyright 2020 OPSLI 快速开发平台 https://www.opsli.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.opsli.common.annotation;

import java.lang.annotation.*;

/**
 * 搜索历史注解
 * 默认按照当前用户 key， 搜索记录排行最高
 *
 * @author Parker
 * @date 2020-09-22 17:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SearchHis {

    /**
     * 搜索 key，即 url 参数key
     *
     * http://127.0.0.1/opsli-boot?username=123
     *
     * username 就是 key
     *
     * 如果使用条件构造器， 比如 username_EQ key需要一致
     *
     */
    String[] keys();

}
