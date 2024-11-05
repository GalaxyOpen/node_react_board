import React from 'react';
import './App.css';
import BoardItem from 'components/BoardItem';
import { CommentListMock, favoriteListMock, latestBoardListMock, top3BoardListMock } from 'mocks';
import Top3Item from 'components/Top3Item';
import CommentItem from 'components/CommentItem';
import FavoriteItem from 'components/FavoriteItem';
import InputBox from 'components/InputBox';


function App() {
  return (
    <>
      {/* {latestBoardListMock.map(boardListItem => <BoardItem boardListItem={boardListItem} />)} */}

      {/* <div style={{ display: 'flex', justifyContent:'center', gap: '24px'}}> 
      {top3BoardListMock.map(top3ListItem => <Top3Item top3ListItem={top3ListItem} />)}
    </div> */}

      {/* <div style={{ padding: '0 20px', display: 'flex', flexDirection: 'column', gap:'30px'}}>
      {CommentListMock.map(CommentListItem => <CommentItem commentListItem={CommentListItem} />)}
      
    </div> */}

      {/* <div style={{display: 'flex', columnGap: '30px', rowGap:'20px' }}>
        {favoriteListMock.map(favoriteListItem => <FavoriteItem favoriteListItem={favoriteListItem} />)}

      </div> */}

      <InputBox />    

    </>
  );
}

export default App;
