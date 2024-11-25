import axios from 'axios';
import { SignInRequestDTO, SignUpRequestDTO } from './request/auth';
import { SignInResponseDTO, SignUpResponseDTO } from './response/auth';
import { ResponseDTO } from './response';
import { response } from 'express';

const DOMAIN = 'http://localhost:4000';

const API_DOMAIN = `${DOMAIN}/api/v1`;

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
    return result;    
}

export const signUpRequest = async (requestBody: SignUpRequestDTO) =>{
    const result = await axios.post(SIGN_UP_URL(), requestBody)
        .then(response =>{
            const responseBody: SignUpResponseDTO = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.resonse.data) return null;
            const responseBody: ResponseDTO = error.response.data;
            return responseBody;
        });
    return result
}