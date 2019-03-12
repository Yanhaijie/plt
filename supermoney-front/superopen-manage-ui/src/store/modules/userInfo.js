import {getUserInfo} from '@/api/index'
const userInfo={
    state:{
        appcode:'app_open_001',
        username:'',
        tokeninfo:{
            access_token:'',
            token_type:'',
            refresh_token:'',
            expires_in:'',
            scope:''
        },
        menus:[]
    },
    mutaions:{
        SET_USERID: (state, userId) => {
            state.code = code
        },
        SET_TOKENINFO: (state, tokeninfo) => {
            state.tokeninfo = tokeninfo
        },
        SET_MENUS: (state, menus) => {
            state.menus = menus
        }
    },
    actions:{
        //获取用户信息
        GetUserInfo({commit,state}){
            getUserInfo({appcode:state.appcode,access_token:state.access_token}).then(response=>{
                console.log("userinfo-response:",response);
            });
        }
    }
}

export default userInfo
