import axios from 'axios';
import { SignInRequestDTO, SignUpRequestDTO } from './request/auth';
import { SignInResponseDTO, SignUpResponseDTO } from './response/auth';
import { ResponseDTO } from './response';
import { GetSignInUserResponseDTO } from './response/user';
import { PostBoardRequestDTO } from './request/board';
import { PostBoardResponseDTO, GetBoardResponseDTO, IncresaeViewCountResponseDTO } from './response/board';
import { request } from 'http';

const DOMAIN = 'http://localhost:4000';
const API_DOMAIN = `${DOMAIN}/api/v1`;

const authorization = (accessToken:string) => {
    return {headers:{Authorization:`Bearer ${accessToken}`}}
}

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
        })
        console.log('Request Body: ', requestBody);
    return result;    
}

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
}

const GET_BOARD_URL = (boardNumber : number | string) => `${API_DOMAIN}/board/${boardNumber}`; // 게시물 상세 페이지 특정 게시물 불러오기 API 연동
const INCREASE_VIEW_COUNT_URL = (boardNumber: number | string ) => `${API_DOMAIN}/board/${boardNumber}/increase-view-count`; // 조회 수 4개 증가 방지용 API 
const POST_BOARD_URL = () =>`${API_DOMAIN}/board`;
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
        })
    return result;  
}

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
        })
    return result;    
}

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
        })
    return result;    
}

const GET_SIGN_IN_USER_URL = () => `${API_DOMAIN}/user`;

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
        })
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
        })
    return result;    
}