import React from 'react';
import './style.css';

// component : Board List Item 컴포넌트  // 
export default function BoardListItem() {

  // render : Board List Item 컴포넌트 렌더링  // 

  return (
    <div className='board-list-item'>
      <div className='board-list-item-main-box'>
        <div className='board-list-item-top'>
          <div className='board-list-item-profile-box'>
            <div className='board-list-item-profile-image' style={{backgroundImage: 'url(https://i.namu.wiki/i/Ht7Gwt4A7gckA36YjEd28fHuAejxMBus82jI8Q37VkbKcpYeWdgtWq0uHfmwzysnN5qIBu-BpO6BrNpjdYhBouSV5sPbodwvNKz-F7Won-RUJr3JIpAC8RD_znjiyQnNyzazrP3aVE5pZyTEM7OaOg.webp)'}}></div>
          </div>
          <div className='board-list-item-write-box'>
            <div className='board-list-item-nickname'>{'The fortress 2 Blue World'}</div>
            <div className='board-list-item-write-datetime'>{'2024.11.04'}</div>
          </div>
        </div>
        <div className='board-list-item-middle'>
          <div className='board-list-item-title'>{'wellcome to e-shibal hell'}</div>
          <div className='board-list-item-content'></div>
        </div>
        <div className='board-list-item-bottom'>
          <div className='board-list-item-counts'>
            {`댓글 0 · 좋아요 0 · 조회수 0`}
          </div>
        </div>
      </div>
      <div className='board-list-item-image'>
        <div className='board-list-item-image' style={{backgroundImage:'url(https://www.google.com/url?sa=i&url=https%3A%2F%2Fm.cafe.daum.net%2Frocksoccer%2FADs1%2F126917%3FlistURI%3D%252Frocksoccer%252FADs1&psig=AOvVaw2m1wvc7f9NxLXvrA3CLA2B&ust=1730764928734000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCLDPjvivwYkDFQAAAAAdAAAAABAI)'}}></div>
      </div>
    </div>
  )
}
