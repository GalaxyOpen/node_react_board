import ResponseDTO from '../response.DTO';

export default interface SignInResponseDTO extends ResponseDTO{
    token: string;
    expirationTime: number;
}