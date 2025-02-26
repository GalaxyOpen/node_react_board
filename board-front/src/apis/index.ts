import axios from 'axios';
import { SignInRequestDTO, SignUpRequestDTO } from './request/auth';
import { SignInResponseDTO, SignUpResponseDTO } from './response/auth';
import { ResponseDTO } from './response';
import { GetSignInUserResponseDTO, GetUserResponseDTO, PatchNicknameResponseDTO, PatchProfileImageResponseDTO } from './response/user';
import { PatchBoardRequestDTO, PostBoardRequestDTO, PostCommentRequestDTO } from './request/board';
import { PostBoardResponseDTO, GetBoardResponseDTO, IncresaeViewCountResponseDTO, GetFavoriteListResponseDTO, GetCommentListResponseDTO, PutFavoriteResponseDTO, DeleteBoardResponseDTO, PatchBoardResponseDTO, GetLatestBoardListResponseDTO, GetTop3BoardListResponseDTO, GetSearchBoardListResponseDTO, GetUserBoardListResponseDTO } from './response/board';
import { GetPopularListResponseDTO, GetRelationListResponseDTO } from './response/search';
import { response } from 'express';
import { PatchNicknameRequestDTO, PatchProfileImageRequestDTO } from './request/user';

const DOMAIN = 'http://localhost:4000';
const API_DOMAIN = `${DOMAIN}/api/v1`;

const authorization = (accessToken:string) => {
    return {headers:{Authorization:`Bearer ${accessToken}`}}
};

const SIGN_IN_URL = () => `${API_DOMAIN}/auth/sign-in`;
const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`;

export const signInRequest = async (requestBody: SignInRequestDTO) => {
    const result = await axios.post(SIGN_IN_URL(), requestBody)
        .then(response => {
            const responseBody: SignInResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if (!error.response.data) return null;
            const responseBody: ResponseDTO = error.response.data;
            return responseBody;
        });
        console.log('Request Body: ', requestBody);
    return result;    
};

export const signUpRequest = async (requestBody: SignUpRequestDTO) =>{
    const result = await axios.post(SIGN_UP_URL(), requestBody)
        .then(response =>{
            const responseBody: SignUpResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.response.data) return null;
            const responseBody: ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;
};

const GET_BOARD_URL = (boardNumber : number | string) => `${API_DOMAIN}/board/${boardNumber}`; // 게시물 상세 페이지 특정 게시물 불러오기 API 연동
const GET_LATEST_BOARD_LIST_URL = () => `${API_DOMAIN}/board/latest-list`;
const GET_TOP_3_BOARD_LIST_URL = () => `${API_DOMAIN}/board/top-3`;
const GET_SEARCH_BOARD_LIST_URL=(searchWord : string, preSearchWord: string | null )=> `${API_DOMAIN}/board/search-list/${searchWord}${preSearchWord ? '/' + preSearchWord : ''}`;
const GET_USER_BOARD_LIST_URL = (email: string) => `${API_DOMAIN}/board/user-board-list/${email}`;
const INCREASE_VIEW_COUNT_URL = (boardNumber: number | string ) => `${API_DOMAIN}/board/${boardNumber}/increase-view-count`; // 조회 수 4개 증가 방지용 API 
const GET_FAVORITE_LIST_URL = (boardNumber : number | string) => `${API_DOMAIN}/board/${boardNumber}/favorite-list`;
const GET_COMMENT_LIST_URL = (boardNumber : number | string) => `${API_DOMAIN}/board/${boardNumber}/comment-list`;
const POST_BOARD_URL = () =>`${API_DOMAIN}/board`;
const POST_COMMENT_URL = (boardNumber : number | string) => `${API_DOMAIN}/board/${boardNumber}/comment`;
const PATCH_BOARD_URL = (boardNumber : number | string) => `${API_DOMAIN}/board/${boardNumber}`;
const PUT_FAVORITE_URL = (boardNumber : number | string) => `${API_DOMAIN}/board/${boardNumber}/favorite`;
const DELETE_BOARD_URL = (boardNumber : number | string) => `${API_DOMAIN}/board/${boardNumber}`;
// 게시물 상세 페이지 특정 게시물 불러오기 API 연동
export const getBoardRequest = async (boardNumber: number | string) =>{
    const result = await axios.get(GET_BOARD_URL(boardNumber))
        .then(response=>{
            const responseBody: GetBoardResponseDTO = response.data;
            return responseBody;
        })
        .catch(error=>{
        if(!error.response) return null;
            const responseBody: ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;  
};

export const getLatestBoardRequest = async()=>{
    const result = await axios.get(GET_LATEST_BOARD_LIST_URL())
        .then(response =>{
            const responseBody : GetLatestBoardListResponseDTO  = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.resonse) return null;
            const responseBody : ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;    
};

export const getTop3BoardListRequest = async () => {
    const result = await axios.get(GET_TOP_3_BOARD_LIST_URL())
        .then(response =>{
            const responseBody : GetTop3BoardListResponseDTO  = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.resonse) return null;
            const responseBody : ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;
};

export const getSearchBoardListRequest = async (searchWord:string, preSearchWord : string | null) => {
    const result = await axios.get(GET_SEARCH_BOARD_LIST_URL(searchWord, preSearchWord))
        .then(response =>{
            const responseBody: GetSearchBoardListResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.response) return null;
            const responseBody : ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;    
};

export const getUserBoardListRequest = async (email : string) => {
    const result = await axios.get(GET_USER_BOARD_LIST_URL(email))
        .then(response =>{
            const responseBody: GetUserBoardListResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.response) return null;
            const responseBody : ResponseDTO = error.resopnse.data;
            return responseBody;
        });
    return result;    
};

export const increaseViewCountRequest = async (boardNumber: number | string) =>{
    const result = await axios.get(INCREASE_VIEW_COUNT_URL(boardNumber))
        .then(response =>{
            const responseBody: IncresaeViewCountResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.response) return null;
            const responseBody : ResponseDTO = error.response.data;
            return responseBody;            
        });
    return result;    
};

export const getFavoriteListRequest = async (boardNumber: number | string) =>{
    const result = await axios.get(GET_FAVORITE_LIST_URL(boardNumber))
        .then(response =>{
            const responseBody: GetFavoriteListResponseDTO = response.data;
            return responseBody;
        })
        .catch(error=>{
            if(!error.response) return null;
            const responseBody : ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;
};

export const GetCommentListRequest = async (boardNumber:number | string) => {
    const result = await axios.get(GET_COMMENT_LIST_URL(boardNumber))
        .then(response=>{
            const responseBody: GetCommentListResponseDTO = response.data;
            return responseBody; 
        })
        .catch(error =>{
            if(!error.response) return null;
            const responseBody : ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;    
};

export const postBoardRequest = async (requestBody: PostBoardRequestDTO, accessToken:string)=>{
    const result = await axios.post(POST_BOARD_URL(), requestBody, authorization(accessToken))
        .then(response =>{
            const responseBody: PostBoardResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if (!error.response) return null;
            const responseBody : ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;    
};

export const postCommentRequest = async (boardNumber : number | string, requestBody: PostCommentRequestDTO, accessToken: string) =>{
    const result = await axios.post(POST_COMMENT_URL(boardNumber), requestBody, authorization(accessToken))
        .then(response =>{
            const responseBody: PutFavoriteResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if (!error.response) return null;
            const responseBody: ResponseDTO =error.response.data;
            return responseBody;
        });
    return result;
};

export const patchBoardRequest = async (boardNumber: number | string, requestBody: PatchBoardRequestDTO, accessToken:string) => {
    const result = await axios.patch(PATCH_BOARD_URL(boardNumber), requestBody, authorization(accessToken))
        .then(response =>{
            const responseBody: PatchBoardResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.response) return null;
            const responseBody: ResponseDTO =error.response.data;
            return responseBody;
        });
    return result;    
};

export const putFavoriteRequest = async (boardNumber : number | string, accessToken: string) =>{
    const result = await axios.put(PUT_FAVORITE_URL(boardNumber),{}, authorization(accessToken))
        .then(response =>{
            const responseBody: PutFavoriteResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if (!error.response) return null;
            const responseBody: ResponseDTO =error.response.data;
            return responseBody;
        });
    return result;    
};

export const deleteBoardRequest = async(boardNumber : number | string, accessToken : string ) =>{
    const result = await axios.delete(DELETE_BOARD_URL(boardNumber), authorization(accessToken))
        .then(response=>{
            const responseBody: DeleteBoardResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.response) return null;
            const responseBody : ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;
};

const GET_POPULAR_LIST_URL = () => `${API_DOMAIN}/search/popular-list`;
const GET_RELATION_LIST_URL = (searchWord: string) => `${API_DOMAIN}/search/${searchWord}/relation-list`;

export const getPopularListRequest = async () =>{
    const result = await axios.get(GET_POPULAR_LIST_URL())
        .then(response=>{
            const responseBody : GetPopularListResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.response) return null;
            const responseBody : ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;    
};

export const getRelationListRequest = async (searchWord : string) =>{
    const result = await axios.get(GET_RELATION_LIST_URL(searchWord)) 
        .then(response =>{
            const responseBody : GetRelationListResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.response) return null;
            const responseBody : ResponseDTO = error.response.data;
            return responseBody;
        });
    return result;    
};

const GET_USER_URL = (email : string) => `${API_DOMAIN}/user/${email}`; 
const GET_SIGN_IN_USER_URL = () => `${API_DOMAIN}/user`;
const PATCH_NICKNAME_URL = () => `${API_DOMAIN}/user/nickname`;
const PATCH_PROFILE_IMAGE_URL = () =>`${API_DOMAIN}/user/profile-image`;

export const getUserRequest = async (email:string) =>{
    const result = await axios.get(GET_USER_URL(email))
        .then(response =>{
            const responseBody: GetUserResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.resopnse) return null;
            const responseBody = error.response.data;
            return responseBody;
        });
    return result;    
};

export const getSignInUserRequest = async (accessToken: string) =>{
    const result = await axios.get(GET_SIGN_IN_USER_URL(), authorization(accessToken))
        .then(response =>{
            const responseBody: GetSignInUserResponseDTO =response.data;
            return responseBody;
        })
        .catch(error =>{
            if (!error.response) return null;
            const responseBody: ResponseDTO =error.response.data;
            return responseBody;
        });
    return result;    
};

export const patchNicknameRequest = async (requestBody : PatchNicknameRequestDTO, accessToken : string) =>{
    const result = await axios.patch(PATCH_NICKNAME_URL(), requestBody, authorization(accessToken))
        .then(response=>{
            const responseBody:PatchNicknameResponseDTO = response.data;
            return responseBody;
        })
        .catch(error=>{
            if(!error.response) return null;
            const responseBody: ResponseDTO =error.response.data;
            return responseBody;
        });
    return result;    
};

export const patchProfileImageReqeust = async (requestBody:PatchProfileImageRequestDTO, accessToken:string) =>{
    const result = await axios.patch(PATCH_PROFILE_IMAGE_URL(), requestBody, authorization(accessToken))
        .then(response=>{
            const responseBody : PatchProfileImageResponseDTO = response.data;
            return responseBody;
        }).catch(error=>{
            if(!error.response) return null;
            const responseBody : ResponseDTO = error.resopnse.data;
            return responseBody;
        });
    return result;    
}

const FILE_DOMAIN = `${DOMAIN}/file`;

const FILE_UPLOAD_URL = () => `${FILE_DOMAIN}/upload`;

const multipartFormData = {headers:{'Content-Type': 'multipart/form-data'}}

export const fileUploadRequest = async (data:FormData) =>{
    const result = await axios.post(FILE_UPLOAD_URL(), data, multipartFormData)
        .then(response =>{
            const responseBody: string = response.data;
            return responseBody;
        })
        .catch(error =>{
            return null;
        });
    return result;    
};