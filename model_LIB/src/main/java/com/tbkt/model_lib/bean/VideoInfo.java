package com.tbkt.model_lib.bean;
/**
 * @Author: DBJ
 * @Date: 2018/6/8 11:18
 * @Description:  保存视频实体类
 *
 */
public class VideoInfo {
    /**
     * 获取视频保存路径
     * @return 返回路径
     */
	public String getPath_name() {
		return path_name;
	}
    /**
     * 设置视频保存路径
     * @return
     */
	public void setPath_name(String path_name) {
		this.path_name = path_name;
	}

    /**
     * 视频保存路径
     */
	private String path_name;
}
