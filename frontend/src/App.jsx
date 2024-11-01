import { useState } from 'react'
// import './App.css'
import Topbar from './_components/topbar'
import WordSearch from './_components/Wordsearch'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Topbar></Topbar>
      <WordSearch></WordSearch>
    </>
  )
}

export default App
