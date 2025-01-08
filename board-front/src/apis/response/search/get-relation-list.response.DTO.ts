import ResponseDTO from '../response.DTO';

export default interface GetRelationListResponseDTO extends ResponseDTO {
    relativeWordList : string[];
}