const API_BASE = '/app';

export async function getHome(userId) {
  const res = await fetch(`${API_BASE}/home?userId=${userId}`);
  return res.json();
}

export async function createRoom(ownerId, roomCode) {
  const body = { ownerId };
  if (roomCode) body.roomCode = roomCode;
  const res = await fetch(`${API_BASE}/room/create`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body),
  });
  return res.json();
}

export async function joinRoom(roomCode, userId) {
  const res = await fetch(
    `${API_BASE}/room/${encodeURIComponent(roomCode)}/join?userId=${encodeURIComponent(
      userId
    )}`,
    { method: 'POST' }
  );
  if (!res.ok) throw new Error('Could not join room');
}

export async function getRoomDetails(roomCode) {
  const res = await fetch(`${API_BASE}/room/${encodeURIComponent(roomCode)}`);
  return res.json();
}

// ASSUMES you add a GET endpoint for fetching a single note
export async function getNote(noteId) {
  const res = await fetch(`${API_BASE}/note/${noteId}`);
  return res.json();
}

export async function createNote(name, ownerId, roomCode) {
  const body = { name, ownerId, roomCode };
  const res = await fetch(`${API_BASE}/note/create`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body),
  });
  return res.json();
}

export async function updateNote(noteId, data) {
  await fetch(`${API_BASE}/note/${noteId}/update`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ data }),
  });
}