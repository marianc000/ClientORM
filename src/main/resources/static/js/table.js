function div(val, clas, id) {
    id = id ? ` id="${id}"` : '';
    clas = clas ? ` class="${clas}"` : '';
    return `<div${id}${clas}>${val??''}</div>`;
}

function row(c, a, ai = 0, p, pi = 0, f, fi = 0) {

    return div(div(c.COUNTRY_NAME, 'country a' + ai + ' p' + pi + ' l' + fi)
            + div(a?.AUTHOR_NAME, 'author a' + ai + ' p' + pi + ' l' + fi)
            + div(p?.POST_NAME, 'post a' + ai + ' p' + pi + ' l' + fi)
            + div(f?.AUTHOR_NAME??f?.FAN_NAME, 'liked a' + ai + ' p' + pi + ' l' + fi), 'row');
}

export function table(data, title) {
    console.log(data);

    return div(div(div(title, 'caption')
            + div(div('Country', 'country')
                    + div('Author', 'author')
                    + div('Post', 'post')
                    + div('Liked by', 'liked')
                    , 'row header')
            + data.flatMap(c => {
                if (!c.authors)
                    return row(c);
                return c.authors.flatMap((a, ai) => {
                    if (!a.posts)
                        return row(c, a, ai);
                    return  a.posts.flatMap((p, pi) => {
                        if (!p.likes)
                            return row(c, a, ai, p, pi);
                        return  p.likes.map((l, li) => row(c, a, ai, p, pi, l, li));
                    });
                });
            }).join(''), 'table ' + title), 'wrapper', title);
}
