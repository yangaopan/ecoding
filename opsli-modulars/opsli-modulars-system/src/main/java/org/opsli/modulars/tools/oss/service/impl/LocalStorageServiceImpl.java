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
package org.opsli.modulars.tools.oss.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.opsli.api.wrapper.system.options.OptionsModel;
import org.opsli.core.autoconfigure.properties.GlobalProperties;
import org.opsli.core.utils.OptionsUtil;
import org.opsli.core.utils.ValidationUtil;
import org.opsli.modulars.tools.oss.conf.LocalConfig;
import org.opsli.modulars.tools.oss.enums.LocalStorageType;
import org.opsli.modulars.tools.oss.enums.OssStorageType;
import org.opsli.modulars.tools.oss.service.BaseOssStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 本地文件上传
 *
 * @author Parker
 * @date 2021年4月30日14:09:08
 */
@Slf4j
public class LocalStorageServiceImpl extends BaseOssStorageService {

    /** 固定路径 */
    private static final String FIXED_PATH = "/static/files";

    @Autowired
    private GlobalProperties globalProperties;

    @Override
    public OssStorageType getType() {
        return OssStorageType.LOCAL;
    }

    @Override
    public String getDomain() {
        return this.getConfig().getDomain();
    }

    @Override
    public FileAttr upload(File file) {
        // 非空判断
        if(FileUtil.isEmpty(file)){
            return new FileAttr();
        }

        // 获得配置信息
        LocalConfig config = this.getConfig();
        // 验证对象
        ValidationUtil.verify(config);

        // 当前时间戳
        long currentTimeMillis = System.currentTimeMillis();

        // 当前时间
        Date currDate = DateUtil.date(currentTimeMillis);

        // 静态路径
        String genPath = globalProperties != null && globalProperties.getWeb() != null &&
                StringUtils.isNotEmpty(globalProperties.getWeb().getUploadPath())
                ? globalProperties.getWeb().getUploadPath()
                : FileUtil.getUserHomePath();

        // 静态路径前缀 默认为空
        String pathPrefix = StringUtils.isNotEmpty(config.getPathPrefix())?config.getPathPrefix():"";

        // 文件夹名称
        String folderName = DateUtil.format(currDate, "yyyyMMdd");

        // 包 全名称
        String packageName = super.handlePath(genPath, false)
                + super.handlePath(FIXED_PATH) + super.handlePath(pathPrefix)
                + super.handlePath(folderName);
        // 包 半名称
        String packageNameByHalf = super.handlePath(FIXED_PATH) + super.handlePath(pathPrefix)
                + super.handlePath(folderName);

        // 文件属性
        FileAttr fileAttr = super.getFileAttr(file);
        // 设置文件路径
        fileAttr.setFileStoragePath(
                config.getDomain() + packageNameByHalf + fileAttr.getName() + fileAttr.getSuffix());


        // 创建文件夹
        FileUtil.mkdir(packageName);

        // 创建文件
        File tmpFile = new File(
                packageName + super.handlePath(fileAttr.getRandomFileNameAndSuffix()));
        try {
            FileUtils.copyFile(file, tmpFile);
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
        FileUtil.touch(tmpFile);

        return fileAttr;
    }

    @Override
    public FileAttr upload(InputStream inputStream, String suffix) {
        // 获得配置信息
        LocalConfig config = this.getConfig();
        // 验证对象
        ValidationUtil.verify(config);

        // 当前时间戳
        long currentTimeMillis = System.currentTimeMillis();

        // 当前时间
        Date currDate = DateUtil.date(currentTimeMillis);

        // 静态路径
        String genPath = globalProperties != null && globalProperties.getWeb() != null &&
                StringUtils.isNotEmpty(globalProperties.getWeb().getUploadPath())
                ? globalProperties.getWeb().getUploadPath()
                : FileUtil.getUserHomePath();

        // 静态路径前缀 默认为空
        String pathPrefix = StringUtils.isNotEmpty(config.getPathPrefix())?config.getPathPrefix():"";

        // 文件夹名称
        String folderName = DateUtil.format(currDate, "yyyyMMdd");

        // 包 全名称
        String packageName = super.handlePath(genPath, false)
                + super.handlePath(FIXED_PATH) + super.handlePath(pathPrefix)
                + super.handlePath(folderName);
        // 包 半名称
        String packageNameByHalf = super.handlePath(FIXED_PATH) + super.handlePath(pathPrefix)
                + super.handlePath(folderName);

        // 文件属性
        FileAttr fileAttr = super.getFileAttr(inputStream, suffix);

        // 设置文件路径
        fileAttr.setFileStoragePath(
                config.getDomain() + packageNameByHalf
                        + super.handlePath(fileAttr.getRandomFileNameAndSuffix()));

        // 创建文件夹
        FileUtil.mkdir(packageName);

        // 创建文件
        File tmpFile = new File(
                packageName + super.handlePath(fileAttr.getRandomFileNameAndSuffix()));
        try {
            FileUtils.copyInputStreamToFile(inputStream, tmpFile);
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
        FileUtil.touch(tmpFile);

        return fileAttr;
    }

    // ===================

    /**
     * 获得配置信息
     * @return 配置
     */
    private LocalConfig getConfig(){
        LocalConfig localConfig = new LocalConfig();

        // 获得配置数据
        OptionsModel domain = OptionsUtil.getOptionByCode(LocalStorageType.DOMAIN.getCode());
        OptionsModel pathPrefix = OptionsUtil.getOptionByCode(LocalStorageType.PATH_PREFIX.getCode());

        if(domain != null){
            localConfig.setDomain(domain.getOptionValue());
        }

        if(pathPrefix != null){
            localConfig.setPathPrefix(pathPrefix.getOptionValue());
        }

        return localConfig;
    }

}
