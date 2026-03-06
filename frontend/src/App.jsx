import { useState } from 'react'
import './App.css'
import Header from './components/Header.jsx';
import Board from './components/Board.jsx'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <Header></Header>
    <Board></Board>
    </>
  )
}

export default App
