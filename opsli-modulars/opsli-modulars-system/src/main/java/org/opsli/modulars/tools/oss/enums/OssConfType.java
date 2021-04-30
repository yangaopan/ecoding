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
package org.opsli.modulars.tools.oss.enums;

/**
 * 存储服务类型
 *
 * @author Parker
 */
public enum OssConfType {

    /** 存储服务类型 */
    STORAGE_TYPE("storage_type", "存储服务类型"),

    ;

    private final String code;
    private final String desc;

    public static OssConfType getType(String cacheType) {
        OssConfType[] var1 = values();
        for (OssConfType type : var1) {
            if (type.code.equalsIgnoreCase(cacheType)) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    // =================

    OssConfType(final String code, final String desc) {
        this.code = code;
        this.desc = desc;
    }
}
