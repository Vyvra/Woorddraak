import raw from '../../public/examplewordsearch.txt'
import React, { useState } from 'react';
function WordSearch() {
  const [WordSearchContent, setWordSearchContent] = useState("")
  let text
  fetch(raw)
    .then(r => r.text())
    .then(t => {
      setWordSearchContent(t);
    })

  return (<><p> {WordSearchContent}</p></>)
}

export default WordSearch
