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
package org.opsli.core.autoconfigure.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Token配置
 *
 * @author Parker
 * @date 2020-09-15
 */
@Configuration
@ConfigurationProperties(prefix = TokenProperties.PROP_PREFIX)
@Data
@EqualsAndHashCode(callSuper = false)
public class TokenProperties {

    public static final String PROP_PREFIX = "opsli.auth.token";

    /** 盐 */
    private String secret;

    /** 有效时间 */
    private int effectiveTime;

}
