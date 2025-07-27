import {
  getHome,
  createRoom,
  joinRoom,
} from './api.js';

function getParam(name) {
  return new URLSearchParams(window.location.search).get(name);
}

async function renderHome() {
  const userId = getParam('userId');
  if (!userId) return alert('No userId supplied');

  const dto = await getHome(userId);

  // Profile
  document.getElementById('profile-name').textContent =
    dto.profile.userId || 'User';

  // Rooms
  const ulOwned = document.getElementById('owned-rooms');
  dto.ownedRooms.forEach(r => {
    const li = document.createElement('li');
    li.innerHTML = `<a href="room.html?noteId=">${r.roomCode}</a>`;
    ulOwned.appendChild(li);
  });

  const ulMember = document.getElementById('member-rooms');
  dto.memberRooms.forEach(r => {
    const li = document.createElement('li');
    li.innerHTML = `<a href="room.html?noteId=">${r.roomCode}</a>`;
    ulMember.appendChild(li);
  });

  // Notes
  const ulNotes = document.getElementById('notes-list');
  dto.notes.forEach(n => {
    const li = document.createElement('li');
    li.innerHTML = `<a href="room.html?noteId=${n.id}">${n.name}</a>`;
    ulNotes.appendChild(li);
  });

  // Activities
  const ulActs = document.getElementById('activities');
  dto.recentActivities.forEach(a => {
    const li = document.createElement('li');
    li.textContent = `${a.type} → ${a.entityId} @ ${new Date(
      a.timestamp
    ).toLocaleString()}`;
    ulActs.appendChild(li);
  });

  // Create room
  document
    .getElementById('create-room-form')
    .addEventListener('submit', async e => {
      e.preventDefault();
      const code = document.getElementById('create-room-code').value.trim();
      await createRoom(userId, code);
      location.reload();
    });

  // Join room
  document
    .getElementById('join-room-form')
    .addEventListener('submit', async e => {
      e.preventDefault();
      const code = document.getElementById('join-room-code').value.trim();
      await joinRoom(code, userId);
      location.reload();
    });
}

document.addEventListener('DOMContentLoaded', renderHome);