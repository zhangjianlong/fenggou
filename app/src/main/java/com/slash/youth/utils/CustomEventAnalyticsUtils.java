package com.slash.youth.utils;

/**
 * Created by zhouyifeng on 2017/1/16.
 */
public class CustomEventAnalyticsUtils {

    public static class EventID {
        public static String CLICK_PAGE_ONE = "CLICK_PAGE_ONE";//查看宣传页1
        public static String CLICK_PAGE_THREE = "CLICK_PAGE_THREE";//查看宣传页3
        public static String CLICK_PAGE_TWO = "CLICK_PAGE_TWO";//查看宣传页2
        public static String IDLE_TIME_ACCESS_REQUIREMENT;//访问需求,首页 需求、服务的切换按钮
        public static String IDLE_TIME_ACCESS_SERVICE;//访问服务,首页 需求、服务的切换按钮
        public static String IDLE_TIME_CLICK_BANNER_ONE;//点击banner1
        public static String IDLE_TIME_CLICK_BANNER_THREE;//点击banner3
        public static String IDLE_TIME_CLICK_BANNER_TWO;//点击banner2
        public static String IDLE_TIME_CLICK_BOTTOM_MORE;//点击底部查看更多
        public static String IDLE_TIME_CLICK_REQUIREMENT_DETAIL = "IDLE_TIME_CLICK_REQUIREMENT_DETAIL";//查看需求详情
        public static String IDLE_TIME_CLICK_RIGHT_MORE;//点击右侧查看更多
        public static String IDLE_TIME_CLICK_SERVICE_DETAIL = "IDLE_TIME_CLICK_SERVICE_DETAIL";//查看服务详情
        public static String IDLE_TIME_MORE_REQUIREMENT_COMPOSITE_DISTACE_NAEREST;//更多需求-排序-距离最近
        public static String IDLE_TIME_MORE_REQUIREMENT_COMPOSITE_PRICE_HIGHEST;//更多需求-排序-价格最高
        public static String IDLE_TIME_MORE_REQUIREMENT_COMPOSITE_RELEASE_TIME_NEAREST;//更多需求-排序-发布时间最近
        public static String IDLE_TIME_MORE_REQUIREMENT_SCREEN_ADDRESS;//更多需求-筛选地址
        public static String IDLE_TIME_MORE_REQUIREMENT_USER_TYPE_ALL_USER;//更多需求-用户类型-全部用户
        public static String IDLE_TIME_MORE_REQUIREMENT_USER_TYPE_APPROVE_USER;//更多需求-用户类型-认证用户
        public static String IDLE_TIME_MORE_SERVICE_RANK_COMPOSITE_EVALUATION_HIGHEST;//更多服务-排序-综合评价最高
        public static String IDLE_TIME_MORE_SERVICE_RANK_DISTACE_NAEREST;//更多服务-排序-距离最近
        public static String IDLE_TIME_MORE_SERVICE_RANK_RELEASE_TIME_NEAREST;//更多服务-排序-发布时间最近
        public static String IDLE_TIME_MORE_SERVICE_SCREEN_ADDRESS;//更多服务-筛选地址
        public static String IDLE_TIME_MORE_SERVICE_TYPE_ALL_SERVICE;//更多服务-类型-全部服务
        public static String IDLE_TIME_MORE_SERVICE_TYPE_OFFLINE_SERVICE;//更多服务-类型-线下服务
        public static String IDLE_TIME_MORE_SERVICE_TYPE_ONLINE_SERVICE;//更多服务-类型-线上服务
        public static String IDLE_TIME_PERSON_MESSAGE_CHAT;//个人信息-聊一聊
        public static String IDLE_TIME_PERSON_MESSAGE_FOCUS_TA;//个人信息-关注TA
        public static String IDLE_TIME_PERSON_MESSAGE_RECOMMEND_FRIEND;//个人信息-推荐给好友
        public static String IDLE_TIME_REQUIREMENT_DETAIL_CHAT = "IDLE_TIME_REQUIREMENT_DETAIL_CHAT";//需求详情-聊一聊
        public static String IDLE_TIME_REQUIREMENT_DETAIL_COLLECT = "IDLE_TIME_REQUIREMENT_DETAIL_COLLECT";//需求详情-收藏
        public static String IDLE_TIME_REQUIREMENT_DETAIL_DISPUTE_CONDUCT_BEHIND_QUESTION;//需求详情-纠纷处理后面问号
        public static String IDLE_TIME_REQUIREMENT_DETAIL_ENTER_PERSON_MESSAGE = "IDLE_TIME_REQUIREMENT_DETAIL_ENTER_PERSON_MESSAGE";//需求详情-进入个人信息
        public static String IDLE_TIME_REQUIREMENT_DETAIL_IMMEDIATELY_GRAB_SINGLE = "IDLE_TIME_REQUIREMENT_DETAIL_IMMEDIATELY_GRAB_SINGLE";//需求详情-立即抢单
        public static String IDLE_TIME_REQUIREMENT_DETAIL_IMMEDIATELY_SHARE = "IDLE_TIME_REQUIREMENT_DETAIL_IMMEDIATELY_SHARE";//需求详情-立即分享
        public static String IDLE_TIME_REQUIREMENT_DETAIL_IMMEDIATELY_SHARE_FRIEND;//需求详情-立即分享-好友
        public static String IDLE_TIME_REQUIREMENT_DETAIL_IMMEDIATELY_SHARE_QQ;//需求详情-立即分享-QQ
        public static String IDLE_TIME_REQUIREMENT_DETAIL_IMMEDIATELY_SHARE_WECHAT;//需求详情-立即分享-微信
        public static String IDLE_TIME_REQUIREMENT_DETAIL_MODIFY = "IDLE_TIME_REQUIREMENT_DETAIL_MODIFY";//需求详情-修改
        public static String IDLE_TIME_REQUIREMENT_DETAIL_RECOMMEND_FRIEND;//需求详情-推荐给好友
        public static String IDLE_TIME_REQUIREMENT_DETAIL_REMARK = "IDLE_TIME_REQUIREMENT_DETAIL_REMARK";//需求详情-备注
        public static String IDLE_TIME_REQUIREMENT_DETAIL_REQUIREMENT_TYPE_ALL_REQUIREMENT;//更多需求-需求类型-全部需求
        public static String IDLE_TIME_REQUIREMENT_DETAIL_REQUIREMENT_TYPE_OFFLINE_REQUIREMENT;//更多需求-需求类型-线下需求
        public static String IDLE_TIME_REQUIREMENT_DETAIL_REQUIREMENT_TYPE_ONLINE_REQUIREMENT;//更多需求-需求类型-线上需求
        public static String IDLE_TIME_REQUIREMENT_DETAIL_SHELVE;//需求详情-上架
        public static String IDLE_TIME_REQUIREMENT_DETAIL_SLASH_FREE_COMMISSION_ACTIVITY;//需求详情-斜杠零佣金活动
        public static String IDLE_TIME_REQUIREMENT_DETAIL_SLASH_SERVICE;//需求详情-斜杠客服
        public static String IDLE_TIME_REQUIREMENT_DETAIL_UNSHELVE = "IDLE_TIME_REQUIREMENT_DETAIL_UNSHELVE";//需求详情-下架
        public static String IDLE_TIME_SEARCH_CLICK_PERSON_SEARCH;//搜索-点击搜人后搜索
        public static String IDLE_TIME_SEARCH_CLICK_SERVICE_SEARCH;//搜索-点击服务后搜索
        public static String IDLE_TIME_SEARCH_CLICKC_REQUIREMENT_SEARCH;//搜索-点击需求后搜索
        public static String IDLE_TIME_SEARCH_DIRECT_SEARCH;//搜索-直接综合搜索
        public static String IDLE_TIME_SERVICE_DETAIL_CHAT = "IDLE_TIME_SERVICE_DETAIL_CHAT";//服务详情-聊一聊
        public static String IDLE_TIME_SERVICE_DETAIL_COLLECT = "IDLE_TIME_SERVICE_DETAIL_COLLECT";//服务详情-收藏
        public static String IDLE_TIME_SERVICE_DETAIL_DISPUTE_CONDUCT_BEHIND_QUESTION;//服务详情-纠纷处理后面问号
        public static String IDLE_TIME_SERVICE_DETAIL_ENTER_PERSON_DETAIL = "IDLE_TIME_SERVICE_DETAIL_ENTER_PERSON_DETAIL";//服务详情-进入个人信息
        public static String IDLE_TIME_SERVICE_DETAIL_IMMEDIATELY_ORDER = "IDLE_TIME_SERVICE_DETAIL_IMMEDIATELY_ORDER";//服务详情-立即预约
        public static String IDLE_TIME_SERVICE_DETAIL_IMMEDIATELY_SHARE;//服务详情-立即分享
        public static String IDLE_TIME_SERVICE_DETAIL_IMMEDIATELY_SHARE_FRIEND;//服务详情-立即分享-好友
        public static String IDLE_TIME_SERVICE_DETAIL_IMMEDIATELY_SHARE_QQ;//服务详情-立即分享-QQ
        public static String IDLE_TIME_SERVICE_DETAIL_IMMEDIATELY_SHARE_WECHAT;//服务详情-立即分享-微信
        public static String IDLE_TIME_SERVICE_DETAIL_RECOMMEND_FRIEND;//服务详情-推荐给好友
        public static String IDLE_TIME_SERVICE_DETAIL_REMARK;//服务详情-备注
        public static String IDLE_TIME_SERVICE_DETAIL_REVISE;//服务详情-修改
        public static String IDLE_TIME_SERVICE_DETAIL_SHELVE;//服务详情-上架
        public static String IDLE_TIME_SERVICE_DETAIL_SLASH_FREE_COMMISSION_ACTIVITY;//服务详情-斜杠零佣金活动
        public static String IDLE_TIME_SERVICE_DETAIL_SLASH_SERVICE;//服务详情-斜杠客服
        public static String IDLE_TIME_SERVICE_DETAIL_UNSHELVE;//服务详情-下架
        public static String MESSAGE_CHAT_CLICK_CALL;//聊天中点击拨打电话
        public static String MESSAGE_CHAT_CLICK_SWITCH_CHARACTER_VOICE;//聊天中点击切换文字和语音
        public static String MESSAGE_CHAT_CLICK_TELEPHONE;//聊天中点击换电话
        public static String MESSAGE_CHAT_CLICK_UPLOAD_PICTURE_PLUS;//聊天中点击上传图片的加号
        public static String MESSAGE_CLICK_MY_MISSON;//点击我的任务
        public static String MESSAGE_CLICK_OTHER_CHAT;//点击其他聊天
        public static String MESSAGE_CLICK_SEARCH;//点击搜索
        public static String MESSAGE_CLICK_SLASH_SIGNPICS;//点击斜杠小助手
        public static String MESSAGE_MY_MISSON_CLICK_MISSON;//我的任务-点击某任务
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_ACCEPT;//我的任务-点击某任务-接受
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_AGREEN_REFUND;//我的任务-点击某任务-同意退款
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_APPEAL;//我的任务-点击某任务-申诉
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_APPLY_REFUND;//我的任务-点击某任务-申请退款
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_CHAT;//我的任务-点击某任务-聊一聊
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_CHOOSE_TA;//我的任务-点击某任务-选择TA
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_CLICK_MISSON_TITLE_CLICK_DETAIL;//我的任务-点击某任务-点击任务标题查看详情
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_COMPLETE_MISSON;//我的任务-点击某任务-完成任务
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_CONFIRM_MISSON;//我的任务-点击某任务-确认任务
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_DELAY_PAY;//我的任务-点击某任务-延期支付
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_ELIMINATE_TA;//我的任务-点击某任务-淘汰TA
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_EVALUATION;//我的任务-点击某任务-评价
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_FINANCE_DETAIL;//我的任务-点击某任务-财务详情
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_MODIFY_CONDITION;//我的任务-点击某任务-修改条件
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_PAY;//我的任务-点击某任务-支付
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_PAY_ALIPAY;//我的任务-点击某任务-支付-支付宝
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_PAY_BALANCE;//我的任务-点击某任务-支付-余额
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_PAY_BALANCE_FORGET_PASSWORD;//我的任务-点击某任务-支付-余额-忘记密码
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_PAY_SET_TRADE_PASSWORD;//我的任务-点击某任务-支付后设置交易密码
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_PAY_WECHAT;//我的任务-点击某任务-支付-微信
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_REJECT;//我的任务-点击某任务-不接受
        public static String MESSAGE_MY_MISSON_CLICK_MISSON_SHARE;//我的任务-点击某任务-分享
        public static String MESSAGE_MY_MISSON_CLICK_RIGHT_MORE;//我的任务-点击右上角更多
        public static String MESSAGE_MY_MISSON_MISSONDETAIL_CLICK_MISSON_CHAT;//我的任务-某个任务-订单详情-聊一聊
        public static String MINE_CLICK_APPROVE;//点击去认证
        public static String MINE_CLICK_APPROVE_BADGE_UPLOAD;//点击去认证-工牌上传
        public static String MINE_CLICK_APPROVE_COMPANY_BUSINESS_CARD_UPLOAD;//点击去认证-公司名片上传
        public static String MINE_CLICK_APPROVE_IDENTITY_CARD_UPLOAD;//点击去认证-身份证上传
        public static String MINE_CLICK_APPROVE_MAILBOX_BACKGROUND_UPLOAD;//点击去认证-邮箱后台上传
        public static String MINE_CLICK_APPROVE_OTHER_EVIDENTIARY_MATERIAL_UPLOAD;//点击去认证-其他证明材料上传
        public static String MINE_CLICK_APPROVE_PERFECT_APPROVE;//点击去认证-去完善认证
        public static String MINE_CLICK_APPROVE_RETURE;//点击去认证-返回
        public static String MINE_CLICK_CHOOSE_OCCUPATION_TYPE;//点击选择职业类型
        public static String MINE_CLICK_EDIT_PROFILE;//点击编辑资料
        public static String MINE_CLICK_HELP;//点击帮助
        public static String MINE_CLICK_HELP_COMMON_PROBLEM;//点击帮助-常见问题
        public static String MINE_CLICK_HELP_CONTACT_US;//点击帮助-联系我们
        public static String MINE_CLICK_HELP_VERSION_UPDATE;//点击帮助-版本更新
        public static String MINE_CLICK_HELP_VERSION_UPDATE_CONFIRM_UPDATE;//点击帮助-版本更新-确定更新
        public static String MINE_CLICK_INFLUENCE;//点击影响力
        public static String MINE_CLICK_MY_ACCOUNT;//点击我的账户
        public static String MINE_CLICK_MY_ACCOUNT_CLICK_MY_FREEZE_MONEY_RIGHT_QUESTION;//点击我的账户-点击冻结金额右侧问号
        public static String MINE_CLICK_MY_ACCOUNT_CLICK_WITHDRAW;//点击我的账户-点击提现
        public static String MINE_CLICK_MY_ACCOUNT_CLICK_WITHDRAW_CLICK_NEXT;//点击我的账户-点击提现-点击下一步
        public static String MINE_CLICK_MY_ACCOUNT_DEAL_RECORD;//点击我的账户-交易记录
        public static String MINE_CLICK_MY_COLLECT;//点击我的收藏
        public static String MINE_CLICK_MY_COLLECT_CLICK_DETAIL;//点击我的收藏-查看详情
        public static String MINE_CLICK_MY_COLLECT_DELETE;//点击我的收藏-删除
        public static String MINE_CLICK_MY_RELEASE_TASK;//点击我发布的任务
        public static String MINE_CLICK_MY_RELEASE_TASK_DELETE;//点击我发布的任务-删除
        public static String MINE_CLICK_MY_RELEASE_TASK_RESHELF;//点击我发布的任务-重新上架
        public static String MINE_CLICK_MY_RELEASE_UNSHELVE;//点击我发布的任务-下架
        public static String MINE_CLICK_PERSON_MESSAGE;//点击查看个人信息
        public static String MINE_CLICK_PERSON_MESSAGE_CLICK_EDIT;//点击查看个人信息-点击编辑
        public static String MINE_CLICK_SET;//点击设置
        public static String MINE_CLICK_SET__SET_TRADE_PASSWORD_RETURE;//点击设置-设置交易密码-返回
        public static String MINE_CLICK_SET_FIND_TRADE_PASSWORD;//点击设置-找回交易密码
        public static String MINE_CLICK_SET_FIND_TRADE_PASSWORD_RETURE;//点击设置-找回交易密码-返回
        public static String MINE_CLICK_SET_FIND_TRADE_PASSWORD_SUBMIT;//点击设置-找回交易密码-提交
        public static String MINE_CLICK_SET_FIND_TRADE_PASSWORD_UPLOAD_PICTURE;//点击设置-找回交易密码-上传照片
        public static String MINE_CLICK_SET_MESSAGE_NO_DISTURB_CLOSE;//点击设置-信息免打扰-关闭
        public static String MINE_CLICK_SET_MESSAGE_NO_DISTURB_OPEN;//点击设置-信息免打扰-开启
        public static String MINE_CLICK_SET_MODIFICATE_TRADE_PASSWORD;//点击设置-修改交易密码
        public static String MINE_CLICK_SET_SET_TRADE_PASSWORD;//点击设置-设置交易密码
        public static String MINE_CLICK_SET_SET_TRADE_PASSWORD_SUBMIT;//点击设置-设置交易密码-提交
        public static String MINE_CLICK_SET_SET_TRADE_PASSWORD_UPLOAD_PICTURE;//点击设置-设置交易密码-上传照片
        public static String MINE_CLICK_SKILL_AGREEMENT;//点击技能管理
        public static String MINE_CLICK_SKILL_AGREEMENT_ADD_SKILL;//点击技能管理-添加技能
        public static String MINE_CLICK_SKILL_AGREEMENT_DELETE;//点击技能管理-删除
        public static String MINE_CLICK_SKILL_AGREEMENT_ISSUE;//点击技能管理-发布
        public static String MINE_CLICK_TELEPHONE_NUMBER;//点击手机号码
        public static String MINE_CLICK_THIRD_PARTY_ACCOUNT;//点击第三方账号
        public static String MINE_CLICK_THIRD_PARTY_ACCOUNT_QQ_BINDING;//点击第三方账号-QQ账号绑定
        public static String MINE_CLICK_THIRD_PARTY_ACCOUNT_QQ_UNBINDING;//点击第三方账号-QQ账号解绑
        public static String MINE_CLICK_THIRD_PARTY_ACCOUNT_QQ_UNBINDING_CONFIRM_UNBUNDING;//点击第三方账号-QQ账号解绑-确定解绑
        public static String MINE_CLICK_THIRD_PARTY_ACCOUNT_WECHAT_BINDING;//点击第三方账号-微信绑定
        public static String MINE_CLICK_THIRD_PARTY_ACCOUNT_WECHAT_UNBINDING;//点击第三方账号-微信解绑
        public static String MINE_CLICK_THIRD_PARTY_ACCOUNT_WECHAT_UNBINDING_CONFIRM_UNBUNDING;//点击第三方账号-微信解绑-确定解绑
        public static String MINE_EDIT_AVATAR;//编辑头像
        public static String MINE_EDIT_COMPANY_POSITION;//编辑公司职位
        public static String MINE_EDIT_INDUSTRY_DIRECTION_SKILL_TAG;//编辑行业方向技能标签
        public static String MINE_EDIT_LOCATION;//编辑所在地
        public static String MINE_EDIT_NAME;//编辑姓名
        public static String MINE_EDIT_RECENT_COMPANY;//编辑最近公司
        public static String MINE_EDIT_SKILL_DESCRIBE;//编辑技能描述
        public static String MINE_EDIT_SLASH_IDENTITY;//编辑斜杠身份
        public static String PUBLISH_REQUIREMENT_CLICK_COMMISSION_ACTIVITY;//发布需求-点击0佣金活动
        public static String PUBLISH_REQUIREMENT_OPEN_INSTALL_ACCOUNT_QUESTION;//发布需求-开启分期到账后问号
        public static String PUBLISH_REQUIREMENT_PUBLISH_MANNER_QUESTION;//发布需求-发布方式后问号
        public static String PUBLISH_REQUIREMENT_SUCCESS_CLICK_DETAIL;//发布需求成功-查看详情
        public static String PUBLISH_REQUIREMENT_SUCCESS_CLOSE;//发布需求成功-关闭
        public static String PUBLISH_REQUIREMENT_SUCCESS_INVITE_FIVE;//发布需求成功-邀请5人
        public static String PUBLISH_REQUIREMENT_SUCCESS_INVITE_FOUR;//发布需求成功-邀请4人
        public static String PUBLISH_REQUIREMENT_SUCCESS_INVITE_ONE;//发布需求成功-邀请1人
        public static String PUBLISH_REQUIREMENT_SUCCESS_INVITE_THREE;//发布需求成功-邀请3人
        public static String PUBLISH_REQUIREMENT_SUCCESS_INVITE_TWO;//发布需求成功-邀请2人
        public static String PUBLISH_SERVICE_CLICK_COMMISSION_ACTIVITY;//发布服务-点击0佣金活动
        public static String PUBLISH_SERVICE_PUBLISH_MANNER_QUESTION;//发布服务-发布方式后问号
        public static String PUBLISH_SERVICE_PUBLISH_OPEN_INSTALL_ACCOUNT_QUESTION;//发布服务-开启分期到账后问号
        public static String PUBLISH_SERVICE_SUCCESS_CLICK_APPROVE;//发布服务成功-点击去认证
        public static String PUBLISH_SERVICE_SUCCESS_CLICK_DETAIL;//发布服务成功-查看详情
        public static String PUBLISH_SERVICE_SUCCESS_CLOSE;//发布服务成功-关闭
        public static String PUBLISH_SERVICE_SUCCESS_INVITE_FIVE;//发布服务成功-邀请5人
        public static String PUBLISH_SERVICE_SUCCESS_INVITE_FOUR;//发布服务成功-邀请4人
        public static String PUBLISH_SERVICE_SUCCESS_INVITE_ONE;//发布服务成功-邀请1人
        public static String PUBLISH_SERVICE_SUCCESS_INVITE_THREE;//发布服务成功-邀请3人
        public static String PUBLISH_SERVICE_SUCCESS_INVITE_TWO;//发布服务成功-邀请2人
        public static String REGISTER_CLICK_ENTER;//注册页点击进入
        public static String REGISTER_CLICK_PICTURE;//注册后点击拍照
        public static String REGISTER_CLICK_QQ_ENTER;//注册页点击QQ进入
        public static String REGISTER_CLICK_SERVICE_AGREEMENT;//注册点击服务协议
        public static String REGISTER_CLICK_VERTIFYCODE;//注册页点击验证码
        public static String REGISTER_CLICK_WECHAT_ENTER;//注册页点击微信进入
        public static String REGISTER_EXCLUSIVE_SKILLS_CHOOSE_INDUSTRY;//注册专属技能选择行业
        public static String REGISTER_EXCLUSIVE_SKILLS_CHOOSE_LABEL;//注册专属技能选择标签
        public static String REGISTER_EXCLUSIVE_SKILLS_CHOOSE_STATION;//注册专属技能选择岗位
        public static String REGISTER_EXCLUSIVE_SKILLS_CLICK_ENTER;//注册专属技能后点击进入首页
        public static String REGISTER_WRITE_NAME_CLICK_COMPLETE;//注册填写姓名后点击完成
        public static String RELATIONSHIP_CLICK_ADD_ME;//点击加我的
        public static String RELATIONSHIP_CLICK_ADD_ME_CLICK_AGREEN;//点击加我的-点击同意
        public static String RELATIONSHIP_CLICK_ADD_ME_CLICK_PERSON_DETAIL;//点击加我的-点击个人详情
        public static String RELATIONSHIP_CLICK_FOCUS_MINE;//点击关注我的
        public static String RELATIONSHIP_CLICK_FOCUS_MINE_CLICK_PERSON_DETAIL;//点击关注我的-点击个人详情
        public static String RELATIONSHIP_CLICK_ME_ADD;//点击我加的
        public static String RELATIONSHIP_CLICK_ME_ADD_CLICK_PERSON_DETAIL;//点击我加的-点击个人详情
        public static String RELATIONSHIP_CLICK_MY_FOCUS;//点击我关注的
        public static String RELATIONSHIP_CLICK_MY_FOCUS_CLICK_PERSON_DETAIL;//点击我关注的-点击个人详情
        public static String RELATIONSHIP_CLICK_SEARCH;//点击搜索
        public static String RELATIONSHIP_RECOMMEND_CLICK_ADD_FRIEND;//在推荐中点击加好友
        public static String RELATIONSHIP_RECOMMEND_CLICK_CHANGE;//在推荐中点击换一批
        public static String RELATIONSHIP_RECOMMEND_CLICK_CLOSE;//在推荐中点击关闭
    }
}
