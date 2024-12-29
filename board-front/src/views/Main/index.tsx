import React, { useEffect, useState } from 'react'
import './style.css'
import Top3Item from 'components/Top3Item'
import { BoardListItem } from 'types/interface';
import { latestBoardListMock, top3BoardListMock } from 'mocks';
import BoardItem from 'components/BoardItem';
import { useNavigate } from 'react-router-dom';
import { SEARCH_PATH } from 'constant';

//       component: 메인 화면 컴포넌트           //
export default function Main() {

  //        function: 네비게이트 함수         //
  const navigate = useNavigate();

  //       component: 메인 화면 상단 컴포넌트           //
  const MainTop = () =>{

    //          state: 주간 Top3 게시물 리스트 상태         //
    const [top3BoardList, setTop3BoardList] = useState<BoardListItem[]>([]);

    //        effect: 첫 마운트 시 실행될 함수         //
    useEffect(()=>{
      setTop3BoardList(top3BoardListMock);
    },[])

    //        render : 메인 화면 상단 컴포넌트 렌더링      //
    return (
      <div id='main-top-wrapper'>
        <div className='main-top-container'>
          <div className='main-top-title'>{'Welcome To \n E-shibal Sports World'}</div>
          <div className='main-top-contents-box'>
            <div className='main-top-contents-title'>{'주간 Top3 게시글'}</div>
            <div className='main-top-contents'>
              {top3BoardList.map(top3ListItem=> <Top3Item top3ListItem={top3ListItem} />)}              
            </div>
          </div>
        </div>
      </div>
    )
  }
  //       component: 메인 화면 하단 컴포넌트           //
  const MainBottom = () =>{

    //          state: 최신 게시물 리스트 상태(임시)         //
    const [currentBoardList, setCurrentBoardList] = useState<BoardListItem[]>([]);
    //          state: 인기 검색어 리스트 상태         //
    const [popularWordList, setpopularWordList] = useState<string[]>([]);

    //        event handler: 인기 검색어 클릭 이벤트 처리         //
    const onPopularWordClickHandler=(word: string)=>{
      navigate(SEARCH_PATH(word));
    }

    //        effect: 첫 마운트 시 실행될 함수         //
    useEffect(()=>{
      setCurrentBoardList(latestBoardListMock);
      setpopularWordList(['welcome','to','see'])
    },[])

    //        render: 메인화면 하단 컴포넌트 렌더링         //
    return (
      <div id='main-bottom-wrapper'>
        <div className='main-bottom-container'>
          <div className='main-bottom-title'>{'최신 게시물'}</div>
          <div className='main-bottom-contents-box'>
            <div className='main-bottom-current-contents'>
              {currentBoardList.map(boardListItem => <BoardItem boardListItem={boardListItem} />)}              
            </div>
            <div className='main-bottom-popular-box'>
              <div className='main-bottom-popular-card'>
                <div className='main-bottom-popular-card-contanier'>
                  <div className='main-bottom-popular-card-title'>{'인기 검색어'}</div>
                  <div className='main-bottom-popular-card-contents'>
                    {popularWordList.map(word => <div className='word-badge' onClick={()=>onPopularWordClickHandler(word)}>{word}</div>)}                    
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className='main-bottom-pagination-box'></div>
          {/* <Pagination /> */}
        </div>
      </div>
    )
  }

  //        render: 메인화면 컴포넌트 렌더링         //
  return (
    <>
      <MainTop />
      <MainBottom />
    </>
  )  
}
