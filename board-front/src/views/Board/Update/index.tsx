import React, { ChangeEvent, useEffect, useRef, useState } from 'react'
import './style.css'
import { useBoardStore, useLoginUserStore } from 'stores';
import { useNavigate, useParams } from 'react-router-dom';
import { MAIN_PATH } from 'constant';
import { useCookies } from 'react-cookie';
import { getBoardRequest } from 'apis';
import { GetBoardResponseDTO } from 'apis/response/board';
import { ResponseDTO } from 'apis/response';
import { convertUrlsToFile } from 'utils';

//       component: 게시물 수정 화면 컴포넌트           //
export default function BoardWrite() {

  //        state : 제목 영역 요소 참조 상태        //
  const titleRef = useRef<HTMLTextAreaElement | null>(null);
  //        state : 본문 영역 요소 참조 상태        //
  const contentRef = useRef<HTMLTextAreaElement | null>(null);
  //        state : 이미지 첨부 요소 참조 상태        //
  const imageInputRef = useRef<HTMLInputElement | null>(null);
  //        state : 게시물 번호 path variable 상태        //
  const { boardNumber } =useParams();
  //        state : 게시물 상태        //
  const { title, setTitle } = useBoardStore();
  const { content, setContent } = useBoardStore();
  const { boardImageFileList, setBoardImageFileList } = useBoardStore();
  //        state : 로그인 유저 상태         //
  const { loginUser } = useLoginUserStore();
  //         state : 쿠키 상태         //
  const [cookies, setCookies] = useCookies();
  //        state :  게시물 이미지 미리보기 상태           //
  const [imageUrls, setImageUrls] = useState<string[]>([]);
  
  //        function : 네비게이트 함수        //
  const navigator = useNavigate();

  //        function : Get Board Response 처리 함수         //
  const getBoardResponse = (responseBody: GetBoardResponseDTO | ResponseDTO | null) =>{
    console.log('Response Body:', responseBody);
    if(!responseBody) return;
    const { code } = responseBody;
    if(code === "NB") alert('존재하지 않는 게시물입니다.');
    if(code === "DBE") alert('데이터베이스 오류입니다.');
    if(code !== "SU") {
      navigator(MAIN_PATH());
      return;
    }
    
    const { title, content, boardImageList, writerEmail}= responseBody as GetBoardResponseDTO;
    setTitle(title);
    setContent(content);
    setImageUrls(boardImageList);
    convertUrlsToFile(boardImageList).then(boardImageFileList => setBoardImageFileList(boardImageFileList));

    if(!loginUser || loginUser.email !== writerEmail) {
      navigator(MAIN_PATH());
      return;
    }

    if (!titleRef.current) return;
    /* textarea 태그의 스크롤바 없애는 기능(시작) */
    titleRef.current.style.height = 'auto';
    titleRef.current.style.height = `${titleRef.current.scrollHeight}px`
    /* textarea 태그의 스크롤바 없애는 기능(끝) */    
  }

  //        event handler : 제목 변경 이벤트 처리         //
  const onTitleChangeHandler =(event: ChangeEvent<HTMLTextAreaElement>)=>{
    const { value } = event.target;
    setTitle(value);

    if (!titleRef.current) return;
    /* textarea 태그의 스크롤바 없애는 기능(시작) */
    titleRef.current.style.height = 'auto';
    titleRef.current.style.height = `${titleRef.current.scrollHeight}px`
    /* textarea 태그의 스크롤바 없애는 기능(끝) */    
  }

  //        event handler: 내용 변경 이벤트 처리          //
  const onContentChangeHandler =(event: ChangeEvent<HTMLTextAreaElement>) =>{
    const {value} = event.target;
    setContent(value);

    if (!contentRef.current) return;
    /* textarea 태그의 스크롤바 없애는 기능(시작) */
    contentRef.current.style.height = 'auto';
    contentRef.current.style.height = `${contentRef.current.scrollHeight}px`
    /* textarea 태그의 스크롤바 없애는 기능(끝) */
  }

  //        event handler: 이미지 변경 이벤트 처리          //
  const onImageChangeHandler = (event: ChangeEvent<HTMLInputElement>) =>{
    if(!event.target.files || !event.target.files.length) return;

    /*미리보기 URL 생성(시작)*/
    const file = event.target.files[0];
    const imageUrl = URL.createObjectURL(file);  
    const newImageUrls = imageUrls.map(item => item);
    newImageUrls.push(imageUrl);
    setImageUrls(newImageUrls);
    /*미리보기 URL 생성(끝)*/ 

    /*실제 이미지 업로드 로직(시작) */
    const newBoardImageFileList = boardImageFileList.map(item=>item);
    newBoardImageFileList.push(file);
    setBoardImageFileList(newBoardImageFileList);
    /*실제 이미지 업로드 로직(끝) */

    if(!imageInputRef.current) return;
    imageInputRef.current.value=''; /* 이 구문이 없으면 같은 이미지를 넣지 못함. */
  }

  //        event handler : 이미지 업로드 버튼 클릭 이벤트 처리         //
  const onImageUploadButtonClickHandler=()=>{
    if(!imageInputRef.current) return;
    imageInputRef.current.click();
  }

  //        event handler : 이미지 닫기 버튼 클릭 이벤트 처리         //
  const onImageCloseButtonClickHandler=(deleteIndex: number)=>{
    if(!imageInputRef.current) return;
    imageInputRef.current.value = '';

    const newImageUrls = imageUrls.filter((url, index)=> index !== deleteIndex);
    setImageUrls(newImageUrls);

    const newBoardImageFileList = boardImageFileList.filter((file, index)=> index !== deleteIndex);
    setBoardImageFileList(newBoardImageFileList);    
  }

  //        effect : 첫 마운트 시 실행할 함수        //
  useEffect(()=>{
    console.log('boardNumber : ', boardNumber); // 1번째 해결책 
    const accessToken = cookies.accessToken;
    if(!accessToken) {
      navigator(MAIN_PATH());
      return;
    }
    if(!boardNumber) return;
    getBoardRequest(boardNumber).then(getBoardResponse);
  },[boardNumber]);

  //        render : 게시물 수정 화면 컴포넌트 렌더링      //
  return (
    <div id='board-update-wrapper'>
      <div className='board-update-container'>
        <div className='board-update-box'>
          <div className='board-update-title-box'>
            <textarea ref={titleRef} className='board-update-title-textarea' rows={1} placeholder='제목을 작성해주세요.' value={title} onChange={onTitleChangeHandler} />
          </div>
          <div className='divider'></div>
          <div className='board-update-content-box'>
            <textarea ref={contentRef} className='board-update-content-textarea' placeholder='본문을 작성해주세요.' value={content} onChange={onContentChangeHandler} />
            <div className='icon-button' onClick={onImageUploadButtonClickHandler}>
              <div className='icon image-box-light-icon'></div>
            </div>
            <input ref={imageInputRef} type='file' accept='image/*' style={{display:'none'}} onChange={onImageChangeHandler}/>
          </div>
          <div className='board-update-images-box'>
            {imageUrls.map((imageUrl,index) => 
              <div key={index} className='board-update-image-box'>
                <img className='board-update-image' src={imageUrl}/>
                <div className='icon-button image-close' onClick={()=>onImageCloseButtonClickHandler(index)}>
                  <div className='icon close-icon'></div>
                </div>
              </div>     
            )}
          </div>
        </div>
      </div>
    </div>
  )
}
