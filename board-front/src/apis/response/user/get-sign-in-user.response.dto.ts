import { User } from 'types/interface';
import ResponseDTO from '../response.DTO';

export default interface GetSignInUserResponseDTO extends ResponseDTO, User {
    
}