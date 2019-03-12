package com.supermoney.open.platform.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_interface")
public class SInterface {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 使用状态（0 使用中，1 禁用 ）
     */
    @Column(name = "use_status")
    private Integer useStatus;

    /**
     * 来源（1 advance , 2 百度, 3 科大讯飞）
     */
    private Integer source;

    /**
     * 类型 （1 黑名单、2 ocr、3 人脸、4 活体）
     */
    @Column(name = "interface_type")
    private Integer interfaceType;

    /**
     * 接口名称
     */
    @Column(name = "interface_name")
    private String interfaceName;

    /**
     * 接口秒描述
     */
    @Column(name = "interface_desc")
    private String interfaceDesc;

    /**
     * 接口url
     */
    @Column(name = "interface_url")
    private String interfaceUrl;

    /**
     * 接口版本
     */
    @Column(name = "interface_version")
    private String interfaceVersion;

    /**
     * 全局提示
     */
    @Column(name = "note_global")
    private String noteGlobal;

    /**
     * 请求提示
     */
    @Column(name = "note_request")
    private String noteRequest;

    /**
     * 返回提示
     */
    @Column(name = "note_respond")
    private String noteRespond;

    /**
     * 请求示例
     */
    @Column(name = "request_example")
    private String requestExample;

    /**
     * 返回示例
     */
    @Column(name = "respond_example")
    private String respondExample;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后修改时间
     *
     * @return update_time - 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param updateTime 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取使用状态（0 使用中，1 禁用 ）
     *
     * @return use_status - 使用状态（0 使用中，1 禁用 ）
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**
     * 设置使用状态（0 使用中，1 禁用 ）
     *
     * @param useStatus 使用状态（0 使用中，1 禁用 ）
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    /**
     * 获取来源（1 advance , 2 百度, 3 科大讯飞）
     *
     * @return source - 来源（1 advance , 2 百度, 3 科大讯飞）
     */
    public Integer getSource() {
        return source;
    }

    /**
     * 设置来源（1 advance , 2 百度, 3 科大讯飞）
     *
     * @param source 来源（1 advance , 2 百度, 3 科大讯飞）
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 获取类型 （1 黑名单、2 ocr、3 人脸、4 活体）
     *
     * @return interface_type - 类型 （1 黑名单、2 ocr、3 人脸、4 活体）
     */
    public Integer getInterfaceType() {
        return interfaceType;
    }

    /**
     * 设置类型 （1 黑名单、2 ocr、3 人脸、4 活体）
     *
     * @param interfaceType 类型 （1 黑名单、2 ocr、3 人脸、4 活体）
     */
    public void setInterfaceType(Integer interfaceType) {
        this.interfaceType = interfaceType;
    }

    /**
     * 获取接口名称
     *
     * @return interface_name - 接口名称
     */
    public String getInterfaceName() {
        return interfaceName;
    }

    /**
     * 设置接口名称
     *
     * @param interfaceName 接口名称
     */
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    /**
     * 获取接口秒描述
     *
     * @return interface_desc - 接口秒描述
     */
    public String getInterfaceDesc() {
        return interfaceDesc;
    }

    /**
     * 设置接口秒描述
     *
     * @param interfaceDesc 接口秒描述
     */
    public void setInterfaceDesc(String interfaceDesc) {
        this.interfaceDesc = interfaceDesc;
    }

    /**
     * 获取接口url
     *
     * @return interface_url - 接口url
     */
    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    /**
     * 设置接口url
     *
     * @param interfaceUrl 接口url
     */
    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    /**
     * 获取接口版本
     *
     * @return interface_version - 接口版本
     */
    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    /**
     * 设置接口版本
     *
     * @param interfaceVersion 接口版本
     */
    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    /**
     * 获取全局提示
     *
     * @return note_global - 全局提示
     */
    public String getNoteGlobal() {
        return noteGlobal;
    }

    /**
     * 设置全局提示
     *
     * @param noteGlobal 全局提示
     */
    public void setNoteGlobal(String noteGlobal) {
        this.noteGlobal = noteGlobal;
    }

    /**
     * 获取请求提示
     *
     * @return note_request - 请求提示
     */
    public String getNoteRequest() {
        return noteRequest;
    }

    /**
     * 设置请求提示
     *
     * @param noteRequest 请求提示
     */
    public void setNoteRequest(String noteRequest) {
        this.noteRequest = noteRequest;
    }

    /**
     * 获取返回提示
     *
     * @return note_respond - 返回提示
     */
    public String getNoteRespond() {
        return noteRespond;
    }

    /**
     * 设置返回提示
     *
     * @param noteRespond 返回提示
     */
    public void setNoteRespond(String noteRespond) {
        this.noteRespond = noteRespond;
    }

    /**
     * 获取请求示例
     *
     * @return request_example - 请求示例
     */
    public String getRequestExample() {
        return requestExample;
    }

    /**
     * 设置请求示例
     *
     * @param requestExample 请求示例
     */
    public void setRequestExample(String requestExample) {
        this.requestExample = requestExample;
    }

    /**
     * 获取返回示例
     *
     * @return respond_example - 返回示例
     */
    public String getRespondExample() {
        return respondExample;
    }

    /**
     * 设置返回示例
     *
     * @param respondExample 返回示例
     */
    public void setRespondExample(String respondExample) {
        this.respondExample = respondExample;
    }
}