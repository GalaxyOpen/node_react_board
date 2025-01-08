import { BoardListItem } from 'types/interface';
import ResponseDTO from '../response.DTO';

export default interface GetSearchBoardListResponseDTO extends ResponseDTO {
    searchList : BoardListItem[];
}