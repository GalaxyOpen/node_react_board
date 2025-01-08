import { BoardListItem } from 'types/interface';
import ResponseDTO from '../response.DTO';

export default interface GetUserBoardListResponseDTO extends ResponseDTO {
    userBoardList : BoardListItem[];
}