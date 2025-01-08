import { BoardListItem } from 'types/interface';
import ResponseDTO from '../response.DTO';

export default interface GetTop3BoardListResponseDTO extends ResponseDTO {
    top3List: BoardListItem[];
}