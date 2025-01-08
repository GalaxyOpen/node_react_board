import { FavoriteListItem } from 'types/interface';
import ResponseDTO from '../response.DTO';

export default interface GetFavoriteListResponseDTO extends ResponseDTO {
    favoriteList: FavoriteListItem[];
}