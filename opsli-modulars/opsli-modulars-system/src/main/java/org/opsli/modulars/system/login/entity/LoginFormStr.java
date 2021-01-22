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
package org.opsli.modulars.system.login.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.opsli.common.annotation.validation.ValidationArgs;
import org.opsli.common.annotation.validation.ValidationArgsLenMax;
import org.opsli.common.annotation.validation.ValidationArgsLenMin;
import org.opsli.common.enums.ValiArgsType;

/**
 * 登录表单
 *
 * @author liuzp
 * @since 2.0.0 2018-01-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginFormStr {

    /** 登录信息 */
    @ApiModelProperty(value = "登录信息")
    @ValidationArgs({ValiArgsType.IS_NOT_NULL})
    private String loginFormStr;

}
