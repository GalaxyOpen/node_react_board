import { CommentListItem } from 'types/interface';
import ResponseDTO from '../response.DTO';

export default interface GetCommentListResponseDTO extends ResponseDTO {
    commentList : CommentListItem[];
}