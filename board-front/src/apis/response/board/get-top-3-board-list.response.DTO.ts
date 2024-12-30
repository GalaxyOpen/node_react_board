import { BoardListItem } from 'types/interface';
import ResponseDTO from '../response.dto';

export default interface GetTop3BoardListResonseDTO extends ResponseDTO {
    top3List: BoardListItem[];
}