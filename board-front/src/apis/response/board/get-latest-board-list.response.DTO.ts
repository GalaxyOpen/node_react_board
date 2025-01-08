import { BoardListItem } from 'types/interface';
import ResponseDTO from '../response.DTO';

export default interface GetLatestBoardListResponseDTO extends ResponseDTO {
    latestList : BoardListItem[];
}