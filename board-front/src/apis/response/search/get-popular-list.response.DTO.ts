import ResponseDTO from '../response.DTO';

export default interface GetPopularListResponseDTO extends ResponseDTO {
    popularWordList : string[];
}