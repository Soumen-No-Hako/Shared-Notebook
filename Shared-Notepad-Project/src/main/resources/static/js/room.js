import { getNote, updateNote } from './api.js';

function getParam(name) {
  return new URLSearchParams(window.location.search).get(name);
}

document.addEventListener('DOMContentLoaded', async () => {
  const noteId = getParam('noteId');
  if (!noteId) return alert('No noteId in URL');

  const note = await getNote(noteId);
  document.getElementById('note-title').textContent = note.name;

  const editor = document.getElementById('note-editor');
  editor.value = note.data || '';

  // STOMP subscription
  const socket = new SockJS('/ws-endpoint');
  const stompClient = Stomp.over(socket);
  stompClient.connect({}, () => {
    stompClient.subscribe('/topic/notes/' + noteId, msg => {
      editor.value = msg.body;
    });
  });

  // Send updates on input (debounce lightly)
  let timeout;
  editor.addEventListener('input', () => {
    clearTimeout(timeout);
    timeout = setTimeout(() => {
      updateNote(noteId, editor.value);
    }, 300);
  });
});