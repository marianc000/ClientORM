import { table } from './table.js';
import {normalizeAuthors} from './transform.js';
import {compare} from './compare.js';

const BASE_URL = '/api/';

const PATHS = ['jpa', 'jdbc', 'jdbcTable'];
const links = '<div class="links">' + PATHS.map(p => `<a href="#${p}">${p}</a>`).join('') + '</div>';
//  
Promise.all(
        PATHS.map(path => fetch(BASE_URL + path).then(r => r.json())))
        .then(ar => {
            compare(ar[0], ar[1]);
            ar[2] = normalizeAuthors(ar[2]);
            compare(ar[1], ar[2]);
            console.log(...ar);

            rootDiv.innerHTML = links + PATHS.map((p, i) => table(ar[i], p)).join('');
        });

 