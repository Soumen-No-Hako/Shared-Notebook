import { getHome, createRoom, joinRoom } from './api.js';

function getParam(name) {
  return new URLSearchParams(window.location.search).get(name);
}

async function renderHome() {
  const userId = getParam('userId');
  if (!userId) {
    alert('User ID missing. Redirecting to login.');
    return window.location.href = 'index.html';
  }

  // Fetch HomeDTO
  const dto = await getHome(userId);

  // Profile
  document.getElementById('profile-name').textContent = dto.profile.name;
  document.getElementById('profile-email').textContent = dto.profile.email;

  // Owned Rooms
  const ownedUl = document.getElementById('owned-rooms');
  ownedUl.innerHTML = '';
  dto.ownedRooms.forEach(r => {
    const li = document.createElement('li');
    li.innerHTML = `
      <a href="room.html?roomCode=${r.roomCode}&userId=${userId}">
        ${r.roomCode}
      </a>
    `;
    ownedUl.appendChild(li);
  });

  // Member Rooms
  const memberUl = document.getElementById('member-rooms');
  memberUl.innerHTML = '';
  dto.memberRooms.forEach(r => {
    const li = document.createElement('li');
    li.innerHTML = `
      <a href="room.html?roomCode=${r.roomCode}&userId=${userId}">
        ${r.roomCode}
      </a>
    `;
    memberUl.appendChild(li);
  });

  // Notes
  const notesUl = document.getElementById('notes-list');
  notesUl.innerHTML = '';
  dto.notes.forEach(n => {
    const li = document.createElement('li');
    li.innerHTML = `
      <a href="room.html?roomCode=${n.roomId}
         &noteId=${n.id}&userId=${userId}">
        ${n.name}
      </a>
    `;
    notesUl.appendChild(li);
  });

  // Recent Activities
  const actsUl = document.getElementById('activities');
  actsUl.innerHTML = '';
  dto.recentActivities.forEach(a => {
    const ts = new Date(a.timestamp).toLocaleString();
    const li = document.createElement('li');
    li.textContent = `${a.userId} ${a.action} ${a.targetId} @ ${ts}`;
    actsUl.appendChild(li);
  });

  // Create Room handler
  document
    .getElementById('create-room-form')
    .addEventListener('submit', async e => {
      e.preventDefault();
      const code = document.getElementById('create-room-code').value.trim();
      try {
        await createRoom(userId, code || undefined);
        renderHome();  // reload
      } catch (err) {
        alert('Could not create room: ' + err);
      }
    });

  // Join Room handler
  document
    .getElementById('join-room-form')
    .addEventListener('submit', async e => {
      e.preventDefault();
      const code = document.getElementById('join-room-code').value.trim();
      try {
        await joinRoom(code, userId);
        renderHome();  // reload
      } catch (err) {
        alert('Could not join room: ' + err);
      }
    });
}

window.addEventListener('DOMContentLoaded', renderHome);